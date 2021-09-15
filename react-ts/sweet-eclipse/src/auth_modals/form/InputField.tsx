import React from 'react';
import {Field} from "formik";

function InputField(props: any) {
    const fieldClassName = "input" + ((props.isInvalid) ? " is-danger" : ((props.isTouched) ? " is-success" : ""));
    const fieldType = props.inputType ? props.inputType : null
    return (
        <div>
            <div className={"control has-icons-right"}>

                <Field className={fieldClassName} name={props.fieldName} type={fieldType}/>

                {props.inner}
            </div>
                {props.outer}
        </div>

    )
}

export default InputField;