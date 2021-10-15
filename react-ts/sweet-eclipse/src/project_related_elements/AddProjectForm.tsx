import React, {useState} from 'react';

function AddProjectForm(props: {
    onSuccessfulCreate?: (data: {} ) => void
}) {

    const [isLoading, setIsLoading] = useState(false);
    const [isError, setIsError] = useState(false);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    function handleSubmitClick(event: any) {
        setIsLoading(true);
        let formData = new FormData();
        formData.append('title', event.target.title.value);
        formData.append('description', event.target.description.value);
        formData.append('fundingGoal', event.target.fundingGoal.value);
        formData.append('files', event.target.fileInputField.files);
        console.log(event.target.title.value, event.target.fundingGoal.value);
        setIsLoading(false);
    }

    return (
        <div>
            <form onSubmit={handleSubmitClick} id="addProjectForm" encType="multipart/form-data">
                <input type="text" name="title" placeholder="title"  /> <br/>
                <input type="text" name="description" placeholder="description"  /><br/>
                <input type="number" name="fundingGoal" placeholder="100.00"/>
                <input type="file" name="fileInputField"  /><br/>
                <button type="submit" className={"button is-primary" + isLoading ? " is-loading" : ""}>
                    Submit project
                </button>
            </form>

            {isError &&
                <div className={"notification is-danger"}>
                    <button className={"delete"} onClick={event => setIsError(false)}/>
                    Error submitting data.
                </div>
            }
        </div>
    );
}


export default AddProjectForm;