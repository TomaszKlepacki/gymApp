import { toHaveErrorMessage } from "@testing-library/jest-dom/dist/matchers"

export default function validateInfo(values) {
    let errors = {}

    if(!values.username.trim()) {
        errors.username = "Wymagany użytkownik"
    }

    //Email
    if(!values.email) {
        errors.email = "Wymagany email"
    } else if(!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)){
        errors.email = "Email jest nieprawidłowy "
    }

    if(!values.password) {
        errors.password = 'Wymagane hasło'
    } else if (values.password.length < 6) {
        errors.password = 'Hasło musi mieć co najmniej 6 znaków'
    }

    if(!values.password2){
        errors.password2 = 'Potwierdź hasło'
    } else if (values.password2 !== values.password){
        errors.password2 = 'Hasło nie pasuje'
    }

    return errors;
}