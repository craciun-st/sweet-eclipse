import React from 'react';
import {Form, Formik, FormikValues} from 'formik';
import * as Yup from 'yup';
import FieldContainer from "./form/FieldContainer";
import InputField from "./form/InputField";
import ValidationIcon from "./form/ValidationIcon";
import ErrorMessageDisplay from "./form/ErrorMessageDisplay";
import {doPostAndProcessResponse} from "../util/Fetching";
import {useHistory} from "react-router-dom";

import {atom, useAtom} from "jotai";


export const shouldSucceedSignUp = atom(true);

const SignupSchema = Yup.object().shape({
    username: Yup.string()
        .min(2, 'Name is too short!')
        .max(50, 'Name is too long!')
        .required('Name is required'),
    email: Yup.string()
        .email('Not a well-formed email address!')
        .required('Email is required'),
    password: Yup.string()
        .min(8, 'Password is too short (should be at least 8 characters)!')
        .max(255, "Excessively long password!")
        .required('Password is required'),
    passwordConfirmation: Yup.string()
        .oneOf([Yup.ref('password')], 'Passwords must match')
        .required('Please confirm your password')
});



function SignUpPage(props: any) {
    const [isUniqueUser, setIsUniqueUser] = useAtom(shouldSucceedSignUp);
    const browserHistory = useHistory();




    return (
        <div>
            <h1>Signup</h1>

            <Formik
                initialValues={{
                    username: '',
                    email: '',
                    password: '',
                    passwordConfirmation: ''
                }}
                validationSchema={SignupSchema}
                onSubmit={values => attemptToSubmit(values)}
            >
                {({errors, touched}) => (

                    <Form>
                        <FieldContainer labelText={"Name"}>
                            <InputField
                                fieldName={"username"}
                                isInvalid={errors.username && touched.username}
                                isTouched={touched.username}
                                requiresCare={!isUniqueUser}
                                inner={
                                    <ValidationIcon
                                        hasIcon={touched.username}
                                        isInvalid={(errors.username && touched.username) || !isUniqueUser}
                                    />
                                }
                                outer={
                                    <div>
                                        <ErrorMessageDisplay
                                            hasError={errors.username && touched.username}
                                            errorMessage={errors.username}
                                        />
                                        <ErrorMessageDisplay
                                            hasError={!isUniqueUser}
                                            errorMessage={"Username must be unique!"}
                                        />
                                    </div>
                                }
                            >
                            </InputField>
                        </FieldContainer>


                        <FieldContainer labelText={"E-mail"}>
                            <InputField
                                fieldName={"email"}
                                isInvalid={errors.email && touched.email}
                                isTouched={touched.email}
                                inner={
                                    <ValidationIcon
                                        hasIcon={touched.email}
                                        isInvalid={errors.email && touched.email}
                                    />
                                }
                                outer={
                                    <ErrorMessageDisplay
                                        hasError={errors.email && touched.email}
                                        errorMessage={errors.email}
                                    />
                                }
                            >
                            </InputField>
                        </FieldContainer>

                        <FieldContainer labelText={"Password"}>
                            <InputField
                                fieldName={"password"}
                                inputType={"password"}
                                isInvalid={errors.password && touched.password}
                                isTouched={touched.password}
                                inner={
                                    <ValidationIcon
                                        hasIcon={touched.password}
                                        isInvalid={errors.password && touched.password}
                                    />
                                }
                                outer={
                                    <ErrorMessageDisplay
                                        hasError={errors.password && touched.password}
                                        errorMessage={errors.password}
                                    />
                                }
                            >
                            </InputField>
                        </FieldContainer>

                        <FieldContainer labelText={"Confirm Password"}>
                            <InputField
                                fieldName={"passwordConfirmation"}
                                inputType={"password"}
                                isInvalid={errors.passwordConfirmation && touched.passwordConfirmation}
                                isTouched={touched.passwordConfirmation}
                                inner={
                                    <ValidationIcon
                                        hasIcon={touched.passwordConfirmation}
                                        isInvalid={errors.passwordConfirmation && touched.passwordConfirmation}
                                    />
                                }
                                outer={
                                    <ErrorMessageDisplay
                                        hasError={errors.passwordConfirmation && touched.passwordConfirmation}
                                        errorMessage={errors.passwordConfirmation}
                                    />
                                }
                            >
                            </InputField>
                        </FieldContainer>

                        <button className="button is-link" type="submit">
                            <strong>Sign Up!</strong>
                        </button>
                    </Form>
                )}
            </Formik>

            <a className="button is-primary is-light" href={"/"}>
                <span className="icon is-small">
                    <i className="fas fa-chevron-left"></i>
                </span>
                <span>Return to Main Page</span>
            </a>
        </div>
    );


    function attemptToSubmit(values: FormikValues) {

        let clientsideData = {
            account: values.username,
            pass: btoa(values.password)
        }
        doPostAndProcessResponse(
            'http://localhost:8080/api/signup',
            values,
            response => handleSignupResponse(response, clientsideData)
        )


    }


    function handleSignupResponse(
        response: Response,
        dataToPersist: { pass: string; account: any },
    ) {

        switch (response.status) {
            case 403:
                setIsUniqueUser(false);
                break;
            case 400:
                window.alert('Could not fulfill request!');
                break;
            case 201:
            case 200:
                localStorage.setItem('sweetEclipse', JSON.stringify(dataToPersist))
                browserHistory.push('/')
                break;
            default:
                window.alert('Something went wrong...');
                browserHistory.push('/');
        }

    }
}


export default SignUpPage;