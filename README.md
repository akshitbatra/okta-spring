# Okta Spring Backend Integration

Java version: 11

Run Application:

`./mvnw spring-boot:run`

Code Flow:
* Go to frontend app (https://okta-login-demo.netlify.app/) or clone from https://github.com/tuhinkarmakar3882/okta-login-sample-frontend
  * If you're going with the live app you just need to go the demo and add the following credentials: 
  
    `client_id: (id for the app using)`
  
    `redirect_url: http://localhost:8080/login/callback/` - For this application
  
     `okta_domain: https://<okta-domain>`
  