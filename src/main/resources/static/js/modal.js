async function getItemModal() {
  $('#modalItem').modal({
    keyboard: true,
    backdrop: 'static',
    show: false
  }).on('show.bs.modal', (event) => {
    let thisModal = $(event.target);
    let id = thisModal.attr('data-id');
    let action = thisModal.attr('data-action');
    switch (action) {
      case 'editItem':
        editItem(thisModal, id);
        break;
      case 'deleteItem':
        deleteItem(thisModal, id);
        break;

    }
  }).on('hidden.bs.modal', (e) => {
    let thisModal = $(e.target);
    thisModal.find('.modal-title-item').html('');
    thisModal.find('.modal-body-item').html('');
    thisModal.find('.modal-footer-item').html('');
  })
}

async function getUserModal() {
  $('#modalUser').modal({
    keyboard: true,
    backdrop: 'static',
    show: false
  }).on('show.bs.modal', (event) => {
    let thisModal = $(event.target);
    let userid = thisModal.attr('data-userid');
    let action = thisModal.attr('data-action');
    switch (action) {
      case 'editUser':
        editUser(thisModal, userid);
        break;
      case 'deleteUser':
        deleteUser(thisModal, userid);
        break;
    }
  }).on('hidden.bs.modal', (e) => {
    let thisModal = $(e.target);
    thisModal.find('.modal-title-user').html('');
    thisModal.find('.modal-body-user').html('');
    thisModal.find('.modal-footer-user').html('');
  })
}


async function getNewUserForm() {
  let button = $(`#addUser`);
  let form = $(`#addUserForm`)
  button.click(() => {
    form.show()
  })
}

async function getNewItemForm() {
  let button = $(`#addItem`);
  let form = $(`#addItemForm`)
  button.click(() => {
    form.show()
  })
}

async function getSizeModal() {
  $('#modalSize').modal({
    keyboard: true,
    backdrop: 'static',
    show: false
  }).on('show.bs.modal', (event) => {
    let thisModal = $(event.target);
    let id = thisModal.attr('data-id');
    let action = thisModal.attr('data-action');
    switch (action) {
      case 'addSize':
        addSize(thisModal)
        break;
      case 'editSize':
        editSize(thisModal, id);
        break;
    }
  }).on('hidden.bs.modal', (e) => {
    let thisModal = $(e.target);
    thisModal.find('.modal-title-size').html('');
    thisModal.find('.modal-body-size').html('');
    thisModal.find('.modal-footer-size').html('');
  })
}

function showSizeModal(event) {
  let modalSize = $('#modalSize');
  let targetButton = $(event);
  console.log(targetButton)
  let buttonItemId = targetButton.attr('data-item-id');
  let buttonAction = targetButton.attr('data-action');
  modalSize.attr('data-id', buttonItemId);
  let attr = modalSize.attr('data-action', buttonAction);
  modalSize.modal('show');

  if (attr === 'addSize') {
  sizes = [];
  }
}

function showItemModal(event) {
  let modalItem = $('#modalItem');
  let targetButton = $(event.target);
  console.log(targetButton)
  let buttonItemId = targetButton.attr('data-id');
  let buttonAction = targetButton.attr('data-action');
  modalItem.attr('data-id', buttonItemId);
  modalItem.attr('data-action', buttonAction);
  modalItem.modal('show');
}

$(document).keydown(function (e) {
  if (e.keyCode === 27) {
    e.stopPropagation();
    $('.popup-fade').fadeOut();
  }
});

$(document).keydown(function (e) {
  if (e.keyCode === 13) {
    e.submit();
    $('.popup-fade').fadeOut();
  }
});

function fillSizeModal(modal, text) {
  modal.find('.modal-title-size').html(text);
  let addButton = `<button class="btn btn-success" type="submit" id="sizeSubmit">Подтвердить</button>`;
  let addElseSize = `<button class="btn btn-info ml-3" type="button" id="addElseSize">Добавить еще размер</button>`;
  let deleteSizeButton = `<button class="btn btn-info ml-3" type="button" id="deleteSizeButton">Удалить размер</button>`;
  let closeButton = `<button type="button" class="btn btn-secondary bl-5" data-dismiss="modal">Закрыть</button>`;
  modal.find('.modal-footer-size').append(addButton);
  modal.find('.modal-footer-size').append(addElseSize);
  modal.find('.modal-footer-size').append(deleteSizeButton);
  modal.find('.modal-footer-size').append(closeButton);
}

function fillItemModal(modal) {
  modal.find('.modal-title-item').html('Изменение товара');
  let editButton = `<button class="btn btn-success btn-left" type="submit" id="editItemButton">Изменить</button>`;
  let closeButton = `<button type="button" class="btn btn-secondary bl-5" data-dismiss="modal">Закрыть</button>`;
  modal.find('.modal-footer-item').append(editButton);
  modal.find('.modal-footer-item').append(closeButton);
}
