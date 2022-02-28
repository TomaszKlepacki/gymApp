import React from "react";
import loginImg from "../../login.svg";

export class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {value: ''};
  
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    alert('A name was submitted: ' + this.state.value);
    event.preventDefault();
  }

  
   

  render() {
    return (
      <div className="base-container" ref={this.props.containerRef}>
        <div className="header">Rejestracja</div>
        <div className="content">
          <div className="image">
            <img src={loginImg} />
          </div>
          
          <div className="form" action="/authorization.jsx">
            <div className="form-group">
              <label htmlFor="username">Użytkownik</label>
              <input type="text" name="username" placeholder="Użytkownik" value={this.state.value} onChange={this.handleChange} />
            </div>
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input type="text" name="email" placeholder="Email" value={this.state.value} onChange={this.handleChange} />
            </div>
            <div className="form-group">
              <label htmlFor="password">Hasło</label>
              <input type="password" name="password" placeholder="Hasło" value={this.state.value} onChange={this.handleChange}/>
            </div>
            <div className="form-group">
              <label htmlFor="password">Potwierdź hasło</label>
              <input type="password" name="password" placeholder="Potwierdź hasło" value={this.state.value} onChange={this.handleChange}/>
            </div>
          
          
        </div>
        <div className="footer">
          <button type="submit" value="Submit" className="btn" >
            Rejestracja
          </button>
        </div>
        </div>
      </div>
      
    );
  }
}
