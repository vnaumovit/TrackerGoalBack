async function createUser() {
    $('#addUser').click(async () =>  {
        let addUserForm = $('#addUserForm')
        const response = await userFetch.addNewUser(userData(addUserForm));
        if (response.ok) {
            await getUsers();
            clearFields(addUserForm);
            addUserForm.prepend(alertMessage('Пользователь успешно добавлен!'));
            $('.nav-tabs a[href="#usersTable"]').tab('show');
        }
    });
}

function clearFields(addUserForm) {
    addUserForm.find('#username').val('');
    addUserForm.find('#password').val('');
    addUserForm.find('#name').val('');
    addUserForm.find('#surname').val('');
    addUserForm.find('#age').val('');
    addUserForm.find('#gender').val('');
    addUserForm.find(checkedRoles()).val('');
    addUserForm.find('#image').val('');
}
