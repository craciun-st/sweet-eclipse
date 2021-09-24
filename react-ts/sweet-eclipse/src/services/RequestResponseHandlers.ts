import {ClientPersistedData} from "../ts-declarations/ClientPersistedData";

function getClientPersistedData() {
    const dataInStorage: string | null = localStorage.getItem("sweetEclipse");
    const data: ClientPersistedData = dataInStorage ? JSON.parse(dataInStorage) : {}
    return data;
}


export function getBasicAuthHeader(data: ClientPersistedData): string {
    let authHeader = ''
    if (data.account && data.pass) {
        const plainTextPass = atob(data.pass);
        authHeader = "Basic " + btoa(data.account + ":" + plainTextPass)
    }
    return authHeader;
}