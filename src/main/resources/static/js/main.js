let roleList = [{ id: 1, role: 'ROLE_USER' }, { id: 2, role: 'ROLE_ADMIN' }]
let pagingNumber = 0;

$(async function () {
  if (window.location.pathname === '/users') {
    $('#usersTable').addClass('show active');
    $('#userTab').addClass('show active');
    await getUsers();
    await createUser();
    await getNewUserForm();
    await getUserModal();
  } else if (window.location.pathname === '/items') {
    $('#itemsTab').addClass('show active');
    $('#itemsTable').addClass('show active');
    await getItems();
    await createItem();
    await getNewItemForm();
    await getItemModal();
    await getSizeModal()
  } else if (window.location.pathname === '/sales') {
    $('#salesTab').addClass('show active');
    $('#salesTable').addClass('show active');
    await getSales();
    await createSale();
    await getNewSaleForm();
    await getSaleModal();
    await getSaleModal()
  } else {
    registration();
  }
})

