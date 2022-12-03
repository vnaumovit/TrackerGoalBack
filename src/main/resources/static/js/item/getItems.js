async function getItems(isFilter, pageNumber) {
  if (pageNumber === undefined) {
    pageNumber = 0
  }
  let pageSize = 10;
  let page = { pageNumber: pageNumber, pageSize: pageSize };
  let items = await itemFetch.getAllItems(page)
    .then(res => res.json())
    .then(items => items);
  if (items.length < pageSize) {
    $('#showElse').prop('hidden', true)
  }
  await itemsForeach(items, isFilter);
  $('#tableAllItems').find('button').on('click', (event) => {
    showMainModal(event);
  })
}

async function itemsForeach(items, isSearch) {
  const table = document.querySelector('#tableAllItems tbody');
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
                            className data-toggle="modal">Изменить</button>
                    </td>
                    <td class="deleteBtn">
                        <button type="button" data-id="${item.id}" data-action="deleteItem" class="btn btn-danger"
                         className data-toggle="modal" >Удалить</button>
                    </td>
                </tr>
            `
    sizeTemp = '';
  });
  if (isSearch) {
    table.innerHTML = temp
  } else {
    table.innerHTML += temp;
  }
}

