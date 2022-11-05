function userData(modal) {
    let checkedRoles = checkRoles();
    let id = modal.find("#id").val();
    let username = modal.find("#username").val().trim();
    let password = modal.find("#password").val().trim();
    let name = modal.find("#name").val().trim();
    let surname = modal.find("#surname").val().trim();
    let age = modal.find("#age").val().trim();
    let phone = modal.find("#phone").val().trim();
    return {
        id: id,
        username: username,
        password: password,
        name: name,
        surname: surname,
        age: age,
        phone: phone,
        roles: checkedRoles()
    };
}


function checkRoles() {
    return () => {
        let array = [];
        let options = document.querySelector('#roles').options;
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                array.push(roleList[i]);
            }
        }
        return array;
    };
}