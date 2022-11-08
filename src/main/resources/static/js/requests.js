const userFetch = {
  head: {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Referer': null
  },
  findAllUsers: async () => await fetch('api/users'),
  findOneUser: async (id) => await fetch(`api/users/${id}`),
  addNewUser: async (user) => await fetch('api/users', {
    method: 'POST',
    headers: userFetch.head,
    body: JSON.stringify(user)
  }),
  updateUser: async (user) => await fetch(`api/users/updateUser`, {
    method: 'PUT',
    headers: userFetch.head,
    body: JSON.stringify(user)
  }),
  deleteUser: async (id) => await fetch(`api/users/${id}`, {
    method: 'DELETE',
    headers: userFetch.head
  })
}

const itemFetch = {
  head: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  },
  getAllItems: async (page) => await fetch('api/items', {
    method: 'POST',
    headers: itemFetch.head,
    body: JSON.stringify(page)
  }),
  getItemById: async (id) => await fetch(`api/items/${id}`),
  getItemsLikeName: async (name, page) => await fetch(`api/items/search?name=${name}`, {
    method: 'POST',
    headers: itemFetch.head,
    body: JSON.stringify(page)
  }),
  getItemsByFilter: async (filter) => await fetch(`api/items/getByFilter`, {
    method: 'POST',
    headers: itemFetch.head,
    body: JSON.stringify(filter)
  }),
  addItem: async (item) => await fetch('api/items/addItem', {
    method: 'POST',
    headers: itemFetch.head,
    body: JSON.stringify(item)
  }),
  updateItem: async (user) => await fetch(`api/items/updateItem`, {
    method: 'PUT',
    headers: itemFetch.head,
    body: JSON.stringify(user)
  }),
  deleteItem: async (id) => await fetch(`api/items/${id}`, {
    method: 'DELETE',
    headers: userFetch.head
  })
}

const sizeFetch = {
  head: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  },
  getSizesByItemId: async (itemId) => await fetch(`api/sizes/getByItemId/${itemId}`),
  updateSizes: async (sizes) => await fetch(`api/sizes/updateSizes`, {
    method: 'PUT',
    headers: itemFetch.head,
    body: JSON.stringify(sizes)
  })
}

const imageFetch = {
  head: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  },
  getImageByItemId: async (itemId) => await fetch(`api/images/byItemId/${itemId}`),
  updateImage: async (image) => await fetch(`api/images/updateImage`, {
    method: 'PUT',
    headers: itemFetch.head,
    body: JSON.stringify(image)
  })
}

const saleFetch = {
  head: {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Referer': null
  },
  findAllSales: async () => await fetch('api/sales'),
  findSaleById: async (id) => await fetch(`api/sales/${id}`),
  addSale: async (user) => await fetch('api/sales', {
    method: 'POST',
    headers: userFetch.head,
    body: JSON.stringify(user)
  }),
  updateSale: async (user, id) => await fetch(`api/sales/${id}`, {
    method: 'PUT',
    headers: userFetch.head,
    body: JSON.stringify(user)
  }),
  deleteSale: async (id) => await fetch(`api/sales/${id}`, {
    method: 'DELETE',
    headers: userFetch.head
  })
}