function userData(modal) {
    let checkedRoles = checkRoles(modal);
    let id = modal.find("#id").val();
    let username = modal.find("#username").val().trim();
    let password = modal.find("#password").val().trim();
    let name = modal.find("#name").val().trim();
    let surname = modal.find("#surname").val().trim();
    let gender = modal.find("#gender").val().trim();
    let age = modal.find("#age").val().trim();
    let phone = modal.find("#phone").val().trim();
    return {
        id: id,
        username: username,
        password: password,
        name: name,
        surname: surname,
        gender: gender,
        age: age,
        phone: phone,
        roles: checkedRoles()
    };
}


function checkRoles(modal) {
    let roleList = [{ id: 1, name: 'ROLE_USER' }, { id: 2, name: 'ROLE_ADMIN' }, {id: 3, name: 'ROLE_SUPER_ADMIN'}]
    return () => {
        let array = []
        let selectRoles = modal.find('#roles').val()
        for (let i = 0; i < roleList.length; i++) {
            for (let selectRole of selectRoles) {
                if (selectRole === roleList[i].name) {
                    array.push(roleList[i]);
                }
            }
        }
        return array;
    };
}