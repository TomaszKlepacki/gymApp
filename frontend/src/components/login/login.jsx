import React from "react";
import loginImg from "../../login.svg";

export class Login extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="base-container" ref={this.props.containerRef}>
        <div className="header">Logowanie</div>
        <div className="content">
          <div className="image">
            <img src={loginImg} />
          </div>
          <div className="form">
            <div className="form-group">
              <label htmlFor="username">Użytkownik</label>
              <input type="text" name="username" placeholder="Podaj nazwę użytkownika" required />
            </div>
            <div className="form-group">
              <label htmlFor="password">Hasło</label>
              <input type="password" name="Hasło" placeholder="Podaj hasło" />
            </div>
          </div>
        </div>
        <div className="footer">
          <button type="button" className="btn">
            Zaloguj się
          </button>
        </div>
      </div>
    );
  }
}
