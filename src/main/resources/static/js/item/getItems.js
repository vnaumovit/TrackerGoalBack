async function getItems() {
  let page = { pageNumber: pagingNumber, pageSize: 15 };
  let items = await itemFetch.getAllItems(page)
    .then(res => res.json())
    .then(items => items);
  await itemsForeach(items);
  $('#tableAllItems').find('button').on('click', (event) => {
    showItemModal(event);
  })
}

async function itemsForeach(items) {
  const table = document.querySelector('#tableAllItems tbody');
  table.innerHTML = '';
  let temp = '';
  let sizeTemp = '';
  items.forEach(item => {
    if (!item.isInStock) {
      sizeTemp = 'Отсутствуют'
    } else {
    item.sizes.forEach(s => {
      sizeTemp += `
            <span>Размер ${s.size}: ${s.quantity}</span>
            <br>
            `
    })
    }
    temp += `
                <tr>
                    <td><img src="data:image/png;base64,${item.image.picture}" height="100" width="125"></td>
                    <td hidden>${item.id}</td>
                    <td>${item.code}</td>
                    <td>${item.name}</td>
                    <td>${item.itemType}</td>
                    <td>${item.retailPrice}</td>
                    <td style="width: 150px">${sizeTemp}</td>
                    <td class="editBtn ">
                        <button type="button" data-id="${item.id}" data-action="editItem" class="btn btn-info"
                            className data-toggle="modal" data-target="#editItemModal">Изменить</button>
                    </td>
                    <td class="deleteBtn">
                        <button type="button" data-id="${item.id}" data-action="deleteItem" class="btn btn-danger"
                         className data-toggle="modal" >Удалить</button>
                    </td>
                </tr>
            `
    sizeTemp = '';
  });
  table.innerHTML = temp;
}

