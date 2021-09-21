export function doGet(
    url: string,
    callback: (data: any) => void
) {
    return fetch(url, {
        method: 'GET',
        mode: 'cors',
        credentials: 'omit',
        headers: {
            'Accept': '*/*'
        }
    })
        .then(response => response.json())
        .then(data => callback(data))
        .catch(err => (console.log("Error while GET from "+url+": "+err)))

}

export function doGetWithBasicAuthCredentials(
    url: string,
    authHeader: string,
    callback: (response: any) => void,
) {
    return fetch(url, {
        method: 'GET',
        mode: 'cors',
        credentials: 'include',
        headers: {
            'Accept': '*/*',
            'Authorization': authHeader
        }
    })
        .then(response => callback(response))
        .catch(err => (console.log("Error while BasicAuth GET from "+url+": "+err.message)))

}

export function doPostAndProcessResponse(
    url: string,
    bodyObj: any,
    callback: (response: Response) => void
) {
    return fetch(url, {
        method: 'POST',
        mode: 'cors',
        credentials: 'omit',
        headers: {
            'Accept': '*/*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyObj)
    })
        .then(response => callback(response))
        .catch(err => (console.log("Error while POST at " + url + ": " + err)));
}


