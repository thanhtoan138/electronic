const app = angular.module("electro-app", []);
app.controller("review-ctrl", function($scope,$http) {	
	$scope.form = {};
	
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/reviews`,item).then(resp => {
			alert("Success!");
		}).catch(error => {
			alert("Please agree to terms and complete all fields");
			console.log("Error", error);
		})
	}
});

app.controller("favorite-ctrl", function($scope,$http) {	
	$scope.formFv = {
		product: { id: "" },
		createDate: new Date(),
		account: { username: "" },
	};
	
	$scope.createFv = function(product_Id,username) {
		$scope.formFv.product.id = product_Id;
		$scope.formFv.account.username = username;
		var item = angular.copy($scope.formFv);
		$http.post(`/rest/favorites`,item).then(resp => {
			alert("Products are saved to your MyFavorites!");
			location.href = window.location.href;
		}).catch(error => {
			alert("Error, sign in lps!");
			console.log("Error", error);
		})
	}
	$scope.deleteFv = function(product_Id,username) {
		$http.delete(`/rest/favorites/${product_Id}/${username}`).then(resp => {
			alert("You deleted MyFavorite!");
			location.href = window.location.href;
		}).catch(error => {
			alert("Error!");
			console.log("Error", error);
		})
	}
	
});

app.controller("shopping-cart-ctrl", function($scope, $http) {
	$scope.cart = {
		items: [],
		//thêm vào giỏ hàng
		add(id) {
			// tìm mặt hàng xem có id như thế k nếu có thì tăng sl
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
			} else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
					
				})
			}
		},
		//xoa san pham
		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		//xóa sạch các sp
		clear() {
			this.items = [];
			this.saveToLocalStorage();
		},
		amt_of(item) { },
		//tính tổng sl các mặt hàng trong giỏ
		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		//tổng thành tiền của các mặt hàng trong giỏ
		get amount() {
			return this.items
				.map(item => (item.price-(item.discount * item.price)/100)*item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : []
		}
	}
	$scope.cart.loadFromLocalStorage();

	$scope.order = {
		createDate: new Date(),
		fullname: "",
		phone: "",
		email: "",
		address: "",
		city: "",
		ordernote: "",
		account: { username: $("#username").text() },
		get orderDetails() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			});
		},
		purchase() {
			//lấy order hiện tại
			var order = angular.copy(this);
			
			//thực hiện đơn hang
			$http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Đặt hàng lỗi!")
                console.log(error)
            })
		}
	}
	
 
  $scope.result = 'pass';
  
  $scope.submitResult = function(result) {
    alert(result)
  };
})