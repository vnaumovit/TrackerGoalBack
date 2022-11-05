async function getUsers() {
  let temp = '';
  const table = document.querySelector('#tableAllUsers tbody');
  await usersForm(temp, table);

  $("#tableAllUsers").find('button').on('click', (event) => {
    let modalUser = $('#modalUser');

    let targetButton = $(event.target);
    let buttonUserId = targetButton.attr('data-userid');
    let buttonAction = targetButton.attr('data-action');

    modalUser.attr('data-userid', buttonUserId);
    modalUser.attr('data-action', buttonAction);
    modalUser.modal('show');
  })
}

async function usersForm(temp, table) {
  await userFetch.findAllUsers()
    .then(res => res.json())
    .then(users => {
      users.forEach(user => {
        temp += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.gender}</td>
                    <td>${user.numberPhone}</td>
                    <td>${user.roles.map(e => " " + e.name.substring(5))}</td>
                    <td>
                        <button type="button" data-userid="${user.id}" data-action="editUser" class="btn btn-info"
                            className data-toggle="modal">Изменить</button>
                    </td>
                    <td>
                        <button type="button" data-userid="${user.id}" data-action="deleteUser" class="btn btn-danger"
                            className data-toggle="modal">Удалить</button>
                    </td>
                </tr>
               `;
      });
      table.innerHTML = temp;

    });
  return temp;
}
