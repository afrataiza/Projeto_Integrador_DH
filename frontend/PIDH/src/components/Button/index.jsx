const Button = ({ text, onClick, type="button"}) => { 
    return(
        <button type={type} onClick={onClick}>{text}</button>
    )
}
export default Button;