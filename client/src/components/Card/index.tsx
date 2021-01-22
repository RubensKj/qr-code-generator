import React, { useEffect, useState, useRef } from 'react';

// Assets
import DownloadIcon from '../../assets/DownloadIcon';

// Services
import api from '../../services/api';

// Styles
import { Container, Input, Button, QRCodeArea } from './styles';

interface QRCode {
  uuid: string;
  urlPath: string;
}

const Card: React.FC = () => {
  const formRef = useRef<HTMLFormElement>(null);
  const inputRef = useRef<HTMLInputElement>(null);
  const [qrContent, setQRContent] = useState<string>('');
  const [qrCode, setQRCode] = useState<QRCode>({} as QRCode);
  const [error, setError] = useState<string | undefined>('');

  useEffect(() => {
    handleClearForm();
  }, []);

  function handleSubmit(event) {
    setError('');
    event.preventDefault();

    if (!qrContent) {
      setError('Please write some content');
    }

    let data = {
      barcodeText: qrContent,
      width: 200,
      height: 200
    }

    api.post<QRCode>('/qrcode/generate', data).then(response => {
      setQRCode(response.data);
    }).catch(error => {
      if (error.errors) {
        setError(error.errors[0].defaultMessage);
        return;
      }
      setError(error.message);
    });
  }

  function handleDownload(uuid: string, urlPath: string) {
    if (!uuid || !urlPath) {
      setError("Cannot download QRCode because wasn't found");
      return;
    }

    let url = urlPath.replace(uuid, '').concat(`/download/${uuid}`);

    window.open(url);
  }

  function handleClearForm() {
    formRef.current?.reset();
    inputRef.current?.focus();
  }

  return (
    <Container>
      <QRCodeArea>
        {qrCode && qrCode.urlPath ? (
          <img src={qrCode.urlPath} alt="QR Code Genereted" />
        ) : (
            <h1>QR Code will appear here</h1>
          )}
      </QRCodeArea>
      <form ref={formRef} onSubmit={handleSubmit}>
        {error && (
          <p className="error">{error}</p>
        )}
        <Input ref={inputRef} onChange={e => setQRContent(e.target.value)} placeholder="QR Code content" />
        {qrCode && qrCode.uuid ? (
          <div className="form-buttons">
            <Button type="submit">Create QR Code</Button>
            <Button onClick={() => handleDownload(qrCode.uuid, qrCode.urlPath)} type="button"><DownloadIcon color="#FFF" /></Button>
          </div>
        ) : (
            <Button type="submit">Create QR Code</Button>
          )}
      </form>
    </Container>
  );
}

export default Card;