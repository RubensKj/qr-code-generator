import React from 'react';

import { Container } from './styles';

// Components
import Card from './components/Card';

// Styles
import Global from './styles/global';

const App: React.FC = () => {
  return (
    <>
      <Container>
        <Card />
      </Container>
      <Global />
    </>
  );
}

export default App;
