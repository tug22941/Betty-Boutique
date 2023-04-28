$( document ).ready(function() {

    const buttons = document.getElementsByTagName("button");

    const signIn = buttons[1];
    const inputs = document.getElementsByTagName("input");
    signIn.addEventListener("click", function () {
      logInputs(noBlanks);
    });

    function logInputs(callBack) {
      console.log(`Email Entered: '${inputs[0].value}'`);
      console.log(`Password Entered: '${inputs[1].value}'`);
      if (inputs[0].value != "" && inputs[1].value != "") {
        callBack(inputs);
      }
    }
    function noBlanks(inp) {
      console.log("ALL FIELDS FILLED: " + inp[0].value + " and " + inp[1].value);
    }
});