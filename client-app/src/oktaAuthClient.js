import {OktaAuth} from '@okta/okta-auth-js';

const authServerId = 'default';
const yourOktaDomain = 'dev-22214085.okta.com';
const clientId = '0oa26nuqtet6pJypD5d7';
const redirectUri = 'http://localhost:8080/login/callback/';

export const oktaAuthClient = new OktaAuth({
    url: `https://${yourOktaDomain}`,
    clientId,
    redirectUri,
    responseType: 'token',
    pkce: false,
    issuer: `https://${yourOktaDomain}/oauth2/${authServerId}`
});