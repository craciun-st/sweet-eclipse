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
                                    inner={
                                        <ValidationIcon
                                            hasIcon={touched.username}
                                            isInvalid={errors.username && touched.username}
                                        />
                                    }
                                    outer={
                                        <ErrorMessageDisplay
                                            hasError={errors.username && touched.username}
                                            errorMessage={errors.username}
                                        />
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

                            <button type="submit">Submit</button>
                        </Form>
                    )}
                </Formik>
            </div>
        );
    }
}



export default SignUpPage;