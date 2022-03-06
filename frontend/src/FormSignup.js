import React from 'react';
import useForm from './useForm';
import validate from './validateInfo';
import './Form.css';

const FormSignup = ({submitForm}) => {
const {handleChange, values, handleSubmit, errors} 
= useForm(
    submitForm,
    validate
);
  return (
   <div className='form-content-right'>
       <form className='form' onSubmit={handleSubmit}>
           <h1>
           Utwórz swoje konto, wypełniając poniższe informacje!
               </h1>
           <div className='form-inputs'>
            <label htmlFor="username"
            className='form-label'>
            Użytkownik
                </label>
                <input 
                id='username'
                type="text"
                name="username"
                className="form-input"
                placeholder='Wprowadź nazwę użytkownika'
                value={values.username}
                onChange={handleChange}
                />
                {errors.username && <p>{errors.username}</p> }
               </div>

               <div className='form-inputs'>
            <label htmlFor="email"
            className='form-label'>
               Email
                </label>
                <input 
                id='email'
                type="email"
                name="email"
                className="form-input"
                placeholder='Wprowadź swój email'
                value={values.email}
                onChange={handleChange}
                />
                {errors.email && <p>{errors.email}</p> }
               </div>

               <div className='form-inputs'>
            <label htmlFor="password"
            className='form-label'>
               Hasło
                </label>
                <input 
                id='password'
                type="password"
                name="password"
                className="form-input"
                placeholder='Wprowadź swoje hasło'
                value={values.password}
                onChange={handleChange}
                />
                {errors.password && <p>{errors.password}</p> }
               </div>
               
               <div className='form-inputs'>
            <label htmlFor="password2"
            className='form-label'>
               Potwierdź hasło
                </label>
                <input 
                id='password2'
                type="password"
                name="password2"
                className="form-input"
                placeholder='Potwierdź hasło'
                value={values.password2}
                onChange={handleChange}
                />
                {errors.password2 && <p>{errors.password2}</p> }
               </div>
            <button className='form-input-btn' type='submit'>
                Zarejestruj się
                </button>
                <span className='form-input-login'>
                    Masz już konto? Zaloguj się <a href="#"> tutaj</a>
                    </span>
           </form>
       </div>

  );
};

export default FormSignup;