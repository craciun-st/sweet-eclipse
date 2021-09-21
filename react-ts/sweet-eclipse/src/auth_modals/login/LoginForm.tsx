import React from 'react';
import {Form, Formik, FormikValues} from 'formik';
import * as Yup from 'yup';
import {useHistory} from "react-router-dom";
import FieldContainer from "../form_elements/FieldContainer";
import InputField from "../form_elements/InputField";
import ValidationIcon from "../form_elements/ValidationIcon";
import ErrorMessageDisplay from "../form_elements/ErrorMessageDisplay";
import {getBasicAuthHeader} from "../../services/RequestResponseHandlers";
import {doGetWithBasicAuthCredentials} from "../../util/Fetching";



const LoginSchema = Yup.object().shape({
    username: Yup.string()
        .required("Don't forget the username"),
    password: Yup.string()
        .required('Password is required'),
});



function LoginForm(props: {
    onSuccessfulCreate?: (dataToPersist: {} ) => void
}) {
    const browserHistory = useHistory();

    const initialMap: { password: string; username: string } = {
        username: '',
        password: ''
    }


    return (
        <div>


            <Formik
                initialValues={initialMap}
                validationSchema={LoginSchema}
                onSubmit={(values, {resetForm}) => attemptToSubmit(values, resetForm)}
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
                                        isInvalid={(errors.username && touched.username)}
                                    />
                                }
                                outer={
                                    <div>
                                        <ErrorMessageDisplay
                                            hasError={errors.username && touched.username}
                                            errorMessage={errors.username}
                                        />
                                    </div>
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



                        <button className="button is-link is-large" type="submit">
                            <strong>Login</strong>
                        </button>
                    </Form>
                )}
            </Formik>


        </div>
    );


    function attemptToSubmit(
        values: FormikValues,
        resetForm: (nextState?: any) => void) {

        let clientsideData = {
            account: values.username,
            pass: btoa(values.password)
        }
        // const requestConfig: AxiosRequestConfig = {
        //     mode: 'cors',
        // }
        // // axios.interceptors.response.eject(useGenericUnauthorizedAlert);
        // axios.get('http://localhost:8080/api/login')
        //     .then( (response: AxiosResponse<any>) => handleLoginResponse(response, clientsideData, resetForm))
        //     .catch((error) => {console.error(error.message)})

        doGetWithBasicAuthCredentials(
            'http://localhost:8080/api/login',
            getBasicAuthHeader(clientsideData),
            (response) => { handleLoginResponse(response, clientsideData, resetForm) }
        )

    }




    function handleLoginResponse(
        response: any,
        dataToPersist: { pass: string; account: any },
        resetForm: (nextState?: {password: string; username: string}) => void
    ) {
        console.log(response);
        switch (response.status) {
            //TODO handle these better
            case 401:
                alert("Unauthorized credentials!")
                resetForm(initialMap)
                break;
            case 400:
                window.alert('Could not fulfill request!');
                break;
            case 200:
                if (props.onSuccessfulCreate) {
                    props.onSuccessfulCreate(dataToPersist)
                    resetForm(initialMap)
                } else {
                    saveInLocalStorage(dataToPersist)
                    resetForm(initialMap)
                }
                break;
            default:
                window.alert('Something went wrong...');
                browserHistory.push('/');
        }

    }

    function saveInLocalStorage(dataToPersist: { pass: string; account: any }) {
        localStorage.setItem('sweetEclipse', JSON.stringify(dataToPersist))
    }
}


export default LoginForm;