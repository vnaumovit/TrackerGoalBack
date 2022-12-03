const tables = [
  { table: '#itemsTab', tab: '#itemsTable', page: '#itemPage' },
  { table: '#salesTab', tab: '#salesTable', page: '#salePage' },
  { table: '#usersTab', tab: '#usersTable', page: '#userPage' }
]

function checkPage(namePage) {
  tables.map(table => {
    if (!table.table.includes(namePage)) {
      $(table.table).removeClass('show active')
      $(table.tab).removeClass('show active')
      $(table.page).attr('hidden', true);
    } else {
      $(table.table).addClass('show active');
      $(table.tab).addClass('show active');
      $(table.page).attr('hidden', false)
    }
  })
}

function checkCache(cache, name) {
  return cache.indexOf(name) === -1;
}

async function loadModals(cache) {
  let modal = 'modal';
  if (checkCache(cache, modal)) {
    await getMainModal();
    await getOptionalModal();
    cache.push(modal)
  }
}

async function itemPage(name, cache) {
  checkPage(name);
  if (checkCache(cache, name)) {
    await getItems();
    await callSearch();
    await callFilter();
    await createItem();
    await getNewItemForm();
    cache.push(name)
  }
}

async function userPage(name, cache) {
  checkPage(name)
  if (checkCache(cache, name)) {
    await getUsers();
    await createUser();
    await getNewUserForm();
    cache.push(name)
  }
}

async function salePage(name, cache) {
  checkPage(name)
  if (checkCache(cache, name)) {
    cache.push(name)
  }
}

async function registrationPage() {
  checkPage('registration')
  registration();
}