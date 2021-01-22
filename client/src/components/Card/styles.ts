import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  max-width: 320px;
  min-height: 340px;
  max-height: 540px;
  width: 100%;

  padding: 12px 15px;

  background: #FFF;
  border-radius: 4px;
  border: 1px solid #ecebed;

  box-shadow: 4px 4px 20px 0 rgba(120,135,182,.12);

  form {
    width: 100%;

    .form-buttons {
      display: grid;
      justify-content: center;
      align-items: center;

      grid-template-columns: 80% 20%;
      grid-gap: 4px;
    }
  }

  .error {
    color: #eb596e;
  }
`;

export const Input = styled.input`
  display: flex;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 38px;

  font: 16px 'Poppins', sans-serif;

  padding: 0 8px;

  border-radius: 4px;
  border: 1px solid #c2bec5;
`;

export const Button = styled.button`
  border: none;
  cursor: pointer;
  color: #FFF;

  display: flex;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 38px;

  font: 16px 'Poppins', sans-serif;

  padding: 0 8px;
  margin-top: 8px;

  background: #252525;
  border-radius: 4px;

  box-shadow: 4px 4px 20px 0 rgba(120,135,182,.12);

  transition: 0.2s transform;

  :hover {
    transform: translateY(-1px);
  }
`;

export const QRCodeArea = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  max-width: 200px;
  min-height: 200px;
  max-height: 200px;
  width: 100%;
  height: 100%;

  padding: 4px;

  margin-bottom: 24px;

  border-radius: 4px;
  border: 1px dashed #c2bec5;

  img {
    width: 100%;
    height: 100%;
    max-width: 200px;
    max-height: 200px;
  }

  h1 {
    text-align: center;

    font-size: 16px;
    font-weight: 300;

    padding: 10px;
  }
`;