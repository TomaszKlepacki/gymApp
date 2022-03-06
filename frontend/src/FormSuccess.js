import React from 'react';
import './Form.css';

const FormSuccess = () => {
  return (
    <div className='form-content-right'>
      <h1 className='form-success'>Na podany email został wysłany link weryfikacyjny </h1>
      <img className='form-img-2' src='img/img-3.svg' alt='success-image' />
    </div>
  );
};

export default FormSuccess;
