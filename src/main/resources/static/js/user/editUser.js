async function editUser(modal, id) {
  validation(modal)
  let request = await userFetch.findOneUser(id);
  let user = request.json();
  modal.find('.modal-title-user').html('Изменение пользователя');
  let editButton = `<button  class="btn btn-info" id="editButton">Edit</button>`;
  let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>`
  modal.find('.modal-footer-user').append(editButton);
  modal.find('.modal-footer-user').append(closeButton);
  userEditForm(user, modal);
  $('#editButton').on('click', async () => {
    let data = userData(modal);
    const response = await userFetch.updateUser(data);
    if (response.ok) {
      await getUsers();
      modal.modal('hide');
      alertMessage('Пользователь успешно изменен')
    }
  })
}

function userEditForm(user, modal) {
  user.then(user => {
    let bodyForm = `
            <form class="form-group text-center" id="editUser">
               <div class="form-group">
                    <label for="id" class="col-form-label">ID</label>
                    <input type="text" class="form-control username" id="id" value="${user.id}" readonly>
               </div>
                   
               <div class="form-group">
                    <label for="username" class="col-form-label">Никнейм</label>
                    <input type="text" class="form-control username" id="username" value="${user.username}">
               </div>

                <div class="form-group">
                    <label for="password" class="com-form-label">Пароль</label>
                    <input type="password" class="form-control" id="password" value="${user.password}">
                </div>

                <div class="form-group">
                    <label for="name" class="com-form-label">Имя</label>
                    <input type="text" class="form-control" id="name" value="${user.name}">
                </div>

                <div class="form-group">
                    <label for="surname" class="com-form-label">Фамилия</label>
                    <input type="text" class="form-control" id="surname" value="${user.surname}">
                </div>

                <div class="form-group">
                    <label for="age" class="com-form-label">Возраст</label>
                    <input type="number" class="form-control" id="age" value="${user.age}">
                </div>
             
                <div class="form-group">
                    <label for="gender" class="label-select">Пол:</label>
                    <select id="gender" class="form-control select" size="2" required>
                        <option value="Male">Мужской</option>
                        <option value="Female">Женский</option>
                        <option value="Unknown">Другой</option>
                    </select>
                    <div class="invalid-feedback">Выберите пол</div>
                    <div class="valid-feedback">Все хорошо!</div>
                </div>

                <div class="form-group">
                    <label for="phone" class="com-form-label">Телефон</label>
                    <input type="tel" class="form-control" id="phone" value="${user.numberPhone}">
                </div>
                
                <div class="form-group">
                    <label for="roles" class="label-select">Роли</label>
                    <select multiple id="roles" size="2" class="form-control" style="max-height: 100px">
                      <option value="ROLE_USER">USER</option>
                      <option value="ROLE_ADMIN">ADMIN</option>
                    </select>
                </div>
            </form>
        `;
    modal.find('.modal-body-user').append(bodyForm);
    modal.find('#gender').val(user.gender)
    modal.find('#roles').val(user.roles.map(r => r.name))
  });
}
