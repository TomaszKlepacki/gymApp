import React from "react";
import styled from "styled-components";
import signUp from "../assets/signUp.svg";
const Main = () => {
  return (
    <Container>
      
      <signUpWrapper>
        <img src={signUp} alt="" />
        <h3>
          GYM <span>APP</span>
        </h3>
      </signUpWrapper>
    </Container>
  );
};

const Container = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

  h1 {
    font-size: 65px;
    font-weight: 900;
    color: #343434;

    @media (max-width: 900px) {
      display: none;
    }
  }
`;

const signUpWrapper = styled.div`
  img {
    height: 6rem;
  }

  h3 {
    color: #ff8d8d;
    text-align: center;
    font-size: 22px;
  }

  span {
    color: #5dc399;
    font-weight: 300;
    font-size: 18px;
  }
`;

export default Main;
