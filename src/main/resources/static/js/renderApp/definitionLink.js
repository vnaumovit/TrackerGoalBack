const navigateTo = (name,cache) => {
  router(name, cache);
}

const router = async (name, cache) => {
  if (location.pathname === '/registration') {
    registrationPage();
  } else {
    await loadModals(cache)
    switch (name) {
      case 'itemsTab':
        await itemPage(name, cache);
        break;
      case 'usersTab':
        await userPage(name, cache);
        break;
      case 'salesTab':
        await salePage(name, cache);
        break;
      default:
        name = 'itemsTab'
        await itemPage(name, cache);
        break;
    }
  }
}

document.addEventListener('DOMContentLoaded', async () => {
  let cache = []
  $('.list-group-flush').on('click', e => {
    if (e.target.matches('[data-link]')) {
      e.preventDefault();
      navigateTo(e.target.id, cache)
    }
  })
  router(null, cache);
})