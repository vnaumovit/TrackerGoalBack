async function getItems() {
  let temp = '';
  console.log(pagingNumber)
  const table = document.querySelector('#tableAllItems tbody');
  let page = { pageNumber: pagingNumber, pageSize: 15 };
  await itemsForeach(page, temp, table);
  $("#tableAllItems").find('button').on('click', (event) => {
    showItemModal(event);
  })
}

async function itemsForeach(page, temp, table) {
  let sizeTemp = '';
  await itemFetch.findAllItems(page)
    .then(res => res.json())
    .then(items => {
      items.forEach(item => {
        item.sizes.forEach(s => {
            sizeTemp += `
            <span>Размер ${s.size}: ${s.quantity}</span>
            <br>
            `
          })
        temp += `
                <tr>
                    <td><img src="data:image/png;base64,${item.image.picture}" height="100" width="125"></td>
                    <td hidden>${item.id}</td>
                    <td>${item.code}</td>
                    <td>${item.name}</td>
                    <td>${item.itemType}</td>
                    <td>${item.retailPrice}</td>
                    <td>${sizeTemp}</td>
                    <td>
                        <button type="button" data-id="${item.id}" data-action="editItem" class="btn btn-info"
                            className data-toggle="modal" data-target="#editItemModal">Изменить</button>
                    </td>
                    <td>
                        <button type="button" data-id="${item.id}" data-action="deleteItem" class="btn btn-danger" className data-toggle="modal" >Удалить</button>
                    </td>
                </tr>
            `
        sizeTemp = '';
      });
      table.innerHTML = temp;
    });
  return temp;
}

