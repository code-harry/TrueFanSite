

// This function contains the logic to decode the JWT token and return the payload
function getJwtPayload(token) 
{
  try 
  {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64).split('').map(c =>
        '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
      ).join('')
    );
    return JSON.parse(jsonPayload);
  } 
  catch (e) 
  {
    console.error("Invalid token format", e);
    return null;
  }
}

// Function to check if a token is expired
function isTokenExpired(token) 
{
  // Getting the payload- which is the second part of the JWT token
  const payload = getJwtPayload(token);

  // If the payload is null or doesn't have an exp field, consider the token expired
  if (!payload || !payload.exp)
    {
      return true;
    }  

    // If the payload is less than the curent time, the token is expired otherwise the token is valid
  return payload.exp < (Date.now() / 1000);

}





// Authentication logic ends here




document.addEventListener('DOMContentLoaded', () => 
    {
        const token = localStorage.getItem('jwtToken');
        if(token==null)
        {
            alert("You need to log in first.");
            window.location.href = '../loginPage/loginPage.html';   
            return;
        }

        if(isTokenExpired(token))
        {
            alert("Session expired. Please log in again.");
            localStorage.removeItem('jwtToken');
             window.location.href = '../Guest/Guest.html'; // go back to guestPage
            return;
        }

        if (token) 
        {
            localStorage.removeItem('jwtToken');
            alert('You have been logged out successfully.');
            window.location.href = '../Guest/Guest.html'; // go back to guestPage
            return;
        }
});
