let etag = null;
let lastEtag = null;
let categories = [];

document.addEventListener('DOMContentLoaded', () => {
	loadCategories();
	refreshProducts();
});

function loadCategories() {
	fetch('/products/categories', {method: 'GET'})
		.then(response => {
			if (!response.ok) {
				throw new Error(`Failed to load categories: ${response.status}`);
			}
			return response.json();
		})
		.then(data => {
			if (!Array.isArray(data)) {
				throw new Error('Expected an array of categories, got: ' + JSON.stringify(data));
			}
			categories = data;
			populateCategoryDropdown('addCategory');
			populateCategoryDropdown('editCategory');
		})
		.catch(error => console.error('Error loading categories:', error));
}

function populateCategoryDropdown(selectId) {
	const select = document.getElementById(selectId);
	select.innerHTML = '';
	categories.forEach(category => {
		const option = document.createElement('option');
		option.value = category.id;
		option.textContent = category.name;
		select.appendChild(option);
	});
}

function refreshProducts() {
	const headers = new Headers();
	if (lastEtag) {
		headers.append('If-None-Match', lastEtag);
	}
	fetch('/products', { headers })
		.then(response => {
			const statusText = response.status + ' ' + response.statusText;
			document.getElementById('status').textContent = 'Status: ' + statusText;

			const etag = response.headers.get('ETag');
			if (etag) {
				document.getElementById('etag').textContent = 'ETag: ' + etag;
				if (response.status === 200) {
					lastEtag = etag; 
				}
			}

			if (response.status === 200) {
				return response.json();
			}
		})
		.then(data => {
			if (data) {
				updateProductTable(data); 
			}
		})
		.catch(error => console.error('Error refreshing products:', error));
}

function updateProductTable(products) {
    const tbody = document.querySelector('#productTable tbody');
    tbody.innerHTML = '';
    products.forEach(product => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
			<td>${product.categoryName} (${product.categoryDesc})</td>
            <td><button onclick="editProduct(${product.id})">Edit</button></td>
        `;
        tbody.appendChild(tr);
    });
}

function addProduct(event) {
    event.preventDefault();
    const product = {
        name: document.getElementById('addName').value,
        price: parseFloat(document.getElementById('addPrice').value),
        categoryId: parseInt(document.getElementById('addCategory').value)
    };
    fetch('/products', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product)
    })
    .then(response => {
        const statusText = response.status + ' ' + response.statusText;
        document.getElementById('addStatus').textContent = 'Status: ' + statusText;
        if (response.ok) {
            refreshProducts();
        }
    })
    .catch(error => console.error('Error adding product:', error));
}

function editProduct(id) {
    fetch(`/products/${id}`, {method: 'GET'})
        .then(response => response.json())
        .then(data => {
            document.getElementById('editId').value = data.id;
            document.getElementById('editName').value = data.name;
            document.getElementById('editPrice').value = data.price;
            document.getElementById('editCategory').value = data.categoryId;
        })
        .catch(error => console.error('Error fetching product:', error));
}

function updateProduct(event) {
    event.preventDefault();
    const id = document.getElementById('editId').value;
    const product = {
        name: document.getElementById('editName').value,
        price: parseFloat(document.getElementById('editPrice').value),
        categoryId: parseInt(document.getElementById('editCategory').value)
    };
    fetch(`/products/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product)
    })
    .then(response => {
        const statusText = response.status + ' ' + response.statusText;
        document.getElementById('editStatus').textContent = 'Status: ' + statusText;
        if (response.ok) {
            refreshProducts();
        }
    })
    .catch(error => console.error('Error updating product:', error));
}