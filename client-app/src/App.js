import {oktaAuthClient} from "./oktaAuthClient";

function App() {
  const handleLogin = async () => {
    await oktaAuthClient.token.getWithRedirect({
      responseType: 'code',
    })
  }
  const url = new URL(window.location);

  const tokenType = url.searchParams.get('token_type');
  const expiresIn = url.searchParams.get('expires_in');
  const accessToken = url.searchParams.get('access_token');
  const scope = url.searchParams.get('scope');
  const idToken = url.searchParams.get('id_token');

    function login() {
        if (accessToken)
        return <>
            <br/>
            {(expiresIn) ? `Expires In : ${expiresIn}` : 'failure'}
            <br/>
            <br/>
            {(idToken) ? `Id Token : ${idToken}` : 'failure'}
            <br/>
            <br/>
            {(accessToken) ? `Access Token : ${accessToken}` : 'failure'}
            <br/>
            <br/>
            {(tokenType) ? `Token Type : ${tokenType}` : 'failure'}
            <br/>
            <br/>
            {(scope) ? `Scope : ${scope}` : 'failure'}
        </>;

        else
            return (<></>);
    }

    return (
    <div>
        <button onClick={handleLogin}>Login</button>
        {login()}
    </div>
  );
}

export default App;
