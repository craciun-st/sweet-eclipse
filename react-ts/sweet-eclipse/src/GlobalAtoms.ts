import {atom} from "jotai";

export const isLoggedInAtom = atom(false)

// Modals
export const isDonateModalActive = atom(false)
export const isSignUpModalActive = atom(false)
export const isLoginModalActive = atom(false)

// Donation data
export const titleForDonationIntent = atom("")
export const imageUriForDonationIntent = atom("https://via.placeholder.com/120x90/eee?text=missing")
export const idForDonationIntent = atom(-1)