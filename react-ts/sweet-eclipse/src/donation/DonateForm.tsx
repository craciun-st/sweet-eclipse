import React from 'react';
import {useAtom} from "jotai";
import {idForDonationIntent, imageUriForDonationIntent, titleForDonationIntent} from "../GlobalAtoms";
import {useHistory} from "react-router-dom";
import {Form, Formik, FormikValues} from "formik";
import FieldContainer from "../auth_modals/form_elements/FieldContainer";
import InputField from "../auth_modals/form_elements/InputField";
import ValidationIcon from "../auth_modals/form_elements/ValidationIcon";
import ErrorMessageDisplay from "../auth_modals/form_elements/ErrorMessageDisplay";
import {doGetWithBasicAuthCredentials, doPostAndProcessResponse} from "../util/Fetching";
import {getBasicAuthHeader} from "../services/RequestResponseHandlers";
import * as Yup from "yup";

function DonateForm(props: any) {

    const [titleForProject, setTitleForProject] = useAtom(titleForDonationIntent);
    const [imageUriForProject, setImageUriForProject] = useAtom(imageUriForDonationIntent);
    const [idForProject, setIdForProject] = useAtom(idForDonationIntent);
    const browserHistory = useHistory();

    const initialMap: { amount: number} = {
        amount: 0,
    }



    const DonationSchema = Yup.object().shape({
        amount: Yup.number()
            .required("Must enter a number for the amount")
            .positive("Amount must be positive")
    })


    return (
        <div>


            <Formik
                initialValues={initialMap}
                validationSchema={DonationSchema}
                onSubmit={(values, {resetForm}) => attemptToSubmit(values, resetForm)}
            >
                {({errors, touched}) => (

                    <Form>
                        <FieldContainer labelText={"Amount (EUR)"}>
                            <InputField
                                fieldName={"amount"}
                                isInvalid={errors.amount && touched.amount}
                                isTouched={touched.amount}
                                inner={<ValidationIcon
                                    hasIcon={touched.amount}
                                    isInvalid={(errors.amount && touched.amount)}
                                />}
                                outer={
                                    <div>
                                        <ErrorMessageDisplay
                                            hasError={errors.amount && touched.amount}
                                            errorMessage={errors.amount}
                                        />
                                    </div>
                                }
                            >
                            </InputField>
                        </FieldContainer>








                        <button className="button is-danger is-large" type="submit">
                            <strong>Mock payment</strong>
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
            amount: values.amount,
            projectId: idForProject
        }

        // console.log(clientsideData)
        
        doPostAndProcessResponse(
            'http://localhost:8080/api/donate/as/anon',
            clientsideData,
            (response) => { handleDonateResponse(response, {}, resetForm) }
        )

    }




    function handleDonateResponse(
        response: any,
        dataToPersist: {},
        resetForm: (nextState?: {amount: number}) => void
    ) {
        console.log(response);
        switch (response.status) {
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
                    resetForm(initialMap)
                    browserHistory.push("/project/"+idForProject)
                }
                break;
            default:
                window.alert('Something went wrong...');
                browserHistory.push('/');
        }

    }

    
}

export default DonateForm;