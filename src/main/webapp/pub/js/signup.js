//regex for names, email, passwords, phone numbers, addresses, and zipcode

// any length chars: no - whitespace, special cahracters
const nameRE = new RegExp(/^[A-Z][-'a-zA-Z]+$/);

//any length chars: must have characters (@ and .), must be 2-3 chars after the '.' character
const emailRE = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);

//8 chars password: at leaset one - uppercase, lowercase, number, special characters
const passwordRE = new RegExp(
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
);

//11 digit phone number: 9 standard - 2 (optional) for country code
const phoneNumberRE = new RegExp(
  /^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$/
);

//any length characters: must not have any special characters besides (# or .)
const addressRE = new RegExp(/^[#.0-9a-zA-Z\s,-]+$/);

//5 digit zipcode with an optional '-'
const zipcodeRE = new RegExp(/^\d{5}(?:[-\s]\d{4})?$/);

//get submit button element: add click event listener: implement action
$( document ).ready(function() {
    $("#btnSubmit").click(function(){
      clearWarnings();

      const reqInputs = document.getElementsByClassName("reqInput");
      let noBlanks = true;
      for (let i = reqInputs.length - 1; i >= 0; i--) {
        let element = reqInputs[i];
        if (IsBlank(element)) {
          displayWarning(element, 0);
          element.focus();
          noBlanks = false;
        }
      }
      if (!noBlanks) {
        return;
      }

      let allValid = true;
      let doFirst = []; //hold first invalid element to direct focus to at function end

      //name validation
      let firstName = document.getElementById("txtFirstName");
      let lastName = document.getElementById("txtLastName");

      if (!validName(firstName)) {
        doFirst.push(firstName, 2);
        allValid = false;
      }
      if (!validName(lastName)) {
        if (doFirst.length == 0) {
          doFirst.push(lastName, 2);
        } else {
          displayWarning(lastName, 2);
        }
        allValid = false;
      }

      //email validation
      let email = document.getElementById("txtEmail");
      if (!validEmail(email)) {
        if (doFirst.length == 0) {
          doFirst.push(email, 2);
        } else {
          displayWarning(email, 2);
        }
        allValid = false;
      }

      //password validation
      let password = document.getElementById("txtPassword");
      let confirmPassword = document.getElementById("txtConfirmPassword");
      if (matchPasswords(password, confirmPassword)) {
        if (!validPassword(password)) {
          if (doFirst.length == 0) {
            doFirst.push(password, 2);
          } else {
            displayWarning(confirmPassword, 2);
            displayWarning(password, 2);
          }
          allValid = false;
        }
      } else {
        displayWarning(confirmPassword, 1);
        displayWarning(password, 1);
        allValid = false;
      }

      //phone number validation
      let phoneNumber = document.getElementById("txtPhoneNumber");
      if (!validPhoneNumber(phoneNumber)) {
        if (doFirst.length == 0) {
          doFirst.push(phoneNumber, 2);
        } else {
          displayWarning(phoneNumber, 2);
        }
        allValid = false;
      }

      //address validation
      let address1 = document.getElementById("txtAddressLine1");
      let address2 = document.getElementById("txtAddressLine2");
      if (!validAddress(address1)) {
        if (doFirst.length == 0) {
          doFirst.push(address1, 2);
        } else {
          displayWarning(address1, 2);
        }
        allValid = false;
      }
      if (address2.value != "") {
        if (!validAddress(address2)) {
          if (doFirst.length == 0) {
            doFirst.push(address2, 2);
          } else {
            displayWarning(address2, 2);
          }
          allValid = false;
        }
      }

      //state validation
      let state = document.getElementById("txtState");
      if (!validName(state)) {
        if (doFirst.length == 0) {
          doFirst.push(state, 2);
        } else {
          displayWarning(state, 2);
        }
        allValid = false;
      }

      //city validation
      let city = document.getElementById("txtCity");
      if (!validName(city)) {
        if (doFirst.length == 0) {
          doFirst.push(city, 2);
        } else {
          displayWarning(city, 2);
        }
        allValid = false;
      }

      //zipcode validation
      let zipcode = document.getElementById("txtZipCode");
      if (!validZipCode(zipcode)) {
        if (doFirst.length == 0) {
          doFirst.push(zipcode, 2);
        } else {
          displayWarning(zipcode, 2);
        }
        allValid = false;
      }

      //validate Input with the first element
      if (doFirst.length != 0) {
        displayWarning(doFirst[0], doFirst[1]);
      }

      //check for all valid input
      if (allValid) {
        document.getElementById("formId").submit();
        console.log("submitted");
      }
    });
});

        //FUNCTIONS

// blank validation function
function IsBlank(element) {
  if (element.value == "") {
    return true;
  } else {
    return false;
  }
}

//regex validations function(s)
function validName(element) {
  if (element.value.match(nameRE)) {
    return true;
  } else {
    return false;
  }
}
function validEmail(element) {
  if (element.value.match(emailRE)) {
    return true;
  } else {
    return false;
  }
}
function matchPasswords(element1, element2) {
  if (element1.value == element2.value) {
    return true;
  } else {
    return false;
  }
}
function validPassword(element) {
  if (element.value.match(passwordRE)) {
    return true;
  } else {
    return false;
  }
}
function validPhoneNumber(element) {
  if (element.value.match(phoneNumberRE)) {
    return true;
  } else {
    return false;
  }
}
function validAddress(element) {
  if (element.value.match(addressRE)) {
    return true;
  } else {
    return false;
  }
}
function validZipCode(element) {
  if (element.value.match(zipcodeRE)) {
    return true;
  } else {
    return false;
  }
}


//display warning function
function displayWarning(element, num) {
  //0: blank field, 1:not matching, 2:incorrect regex pattern

  switch (element.id) {
    case "txtFirstName":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("firstNameHelp").textContent =
          "First Name Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("firstNameHelp").textContent =
          "Name Must Not Contain Special Characters Digits";
      }
      break;
    case "txtLastName":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("lastNameHelp").textContent =
          "Last Name Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("lastNameHelp").textContent =
          "Name Must Not Contain Special Characters Digits";
      }
      break;
    case "txtEmail":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("emailHelp").textContent =
          "Email Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("emailHelp").textContent =
          "Invalid Email Format: Ex) John365@xy.org";
      }
      break;
    case "txtPassword":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("passwordHelp").textContent =
          "Password Field Can Not Be Blank";
      }
      if (num == 1) {
        document.getElementById("passwordHelp").textContent =
          "Password Fields Must Match";
      }
      if (num == 2) {
        document.getElementById("passwordHelp").textContent =
          "Password Must Be Minimum 8 Characters W/ At Least 1: Uppercase, Lowercase, Digit, and Number ";
      }
      break;
    case "txtConfirmPassword":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("confirmPasswordHelp").textContent =
          "Confirm Password Field Can Not Be Blank";
      }
      if (num == 1) {
        document.getElementById("confirmPasswordHelp").textContent =
          "Password Fields Must Match";
      }
      if (num == 2) {
        document.getElementById("confirmPasswordHelp").textContent =
          "Password Must Be Minimum 8 Characters W/ At Least 1: Uppercase, Lowercase, Digit, and Number ";
      }
      break;
    case "txtPhoneNumber":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("phoneNumberHelp").textContent =
          "Phone Number Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("phoneNumberHelp").textContent =
          "# Must Be Minimum 9 Digits W/ Or W/O Seperators";
      }
      break;
    case "txtAddressLine1":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("addressLine1Help").textContent =
          "Address Line 1 Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("addressLine1Help").textContent =
          "Address Must Only Include Special Characters: #- and .";
      }
      break;
    case "txtAddressLine2":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("addressLine2Help").textContent =
          "Address Line 1 Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("addressLine2Help").textContent =
          "Address Must Only Include Special Characters: #- and .";
      }
      break;
    case "ddlCountry":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("countryHelp").textContent =
          "Country Field Must Not Be Blank";
      }
      break;
    case "txtState":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("stateHelp").textContent =
          "State Field Must Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("stateHelp").textContent =
          "State Must Not Contain Special Characters Digits";
      }
      break;
    case "txtCity":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("cityHelp").textContent =
          "City Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("cityHelp").textContent =
          "City Must Not Contain Special Characters Digits";
      }
      break;
    case "txtZipCode":
      element.focus();
      element.classList.add("is-invalid");
      if (num == 0) {
        document.getElementById("zipCodeHelp").textContent =
          "ZipCode Field Can Not Be Blank";
      }
      if (num == 2) {
        document.getElementById("zipCodeHelp").textContent =
          "ZipCode Must Be 5 Digits";
      }
      break;
    default:
  }
}

//clear warnings function
function clearWarnings() {
  const input = document.getElementsByClassName("input");
  for (let i = 0; i < input.length; i++) {
    let element = input[i];
    element.classList.remove("is-invalid");
  }

  const field = document.getElementsByClassName("field");
  for (let i = 0; i < field.length; i++) {
    let element = field[i];
    element.textContent = "";
  }
}

