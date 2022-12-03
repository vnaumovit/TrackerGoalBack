async function deleteItem(modal, id) {
    let request = await itemFetch.getItemById(id);
    let item = request.json();
    modal.find('.modal-title').html('Удаление товара');
    let deleteButton = `<button  class="btn btn-danger" id="deleteButton">Удаление</button>`;
    let closeButton = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>`
    modal.find('.modal-footer').append(deleteButton);
    modal.find('.modal-footer').append(closeButton);
    itemDeleteForm(item, modal);
    $("#deleteButton").on('click', async () => {
        const response = await itemFetch.deleteItem(id);
        if (response.ok) {
            await getItems(true);
            modal.modal('hide');
        }
    })

}

function itemDeleteForm(item, modal) {
    item.then(item => {
        let bodyForm = `
            <form class="form-group text-center" id="deleteItem">          
                <div class="form-group">
                    <label for="name" class="col-form-label">Название</label>
                    <input type="text" class="form-control name" id="name" value="${item.name}" readonly>
                </div>   
        
                <div class="form-group">
                    <label for="itemTypeCreate" class="label-select">Тип</label>
                    <select id="itemTypeCreate" class="form-control" size="1">
                        <option>${item.itemType}</option>
                    </select disabled>
                </div>
        
                <div class="form-group">
                    <label for="retailPriceCreate" class="com-form-label">Продажная цена</label>
                    <input type="number" class="form-control" id="retailPriceCreate" value="${item.retailPrice}" readonly>
                </div>
            </form>
        `;
        modal.find('.modal-body').append(bodyForm);
    });
}
