# Okta Spring Backend Integration

Java version: 11

Run Application:

`./mvnw spring-boot:run`

Frontend Installation Instruction

`cd client-app`

`npm i`

`npm start`

Code Flow:
* Go to frontend app (https://okta-login-demo.netlify.app/) or clone from https://github.com/tuhinkarmakar3882/okta-login-sample-frontend
  * If you're going with the live app you just need to go the demo and add the following credentials: 
  
    `client_id: (id for the app using)`
  
    `redirect_url: http://localhost:8080/login/callback/` - For this application
  
    `okta_domain: https://<okta-domain>`
  
* Go the http://localhost:3000 and click on login button
  * It will redirect to the okta login screen
  * Enter credentials and it will redirect back to the frontend with the tokens.


In order to use (https://okta-login-demo.netlify.app/) online you need to change:

  `String frontendRedirectionUrl = "https://okta-login-demo.netlify.app/login/callback";`
  
  In AuthController on line 32.
  
P.S: Whatever url is being used for frontend do not forget to add it to trusted origins on okta.