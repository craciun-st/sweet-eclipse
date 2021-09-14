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

