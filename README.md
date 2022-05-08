# Project - Dorset College
- Module Title: Mobile App 1
- Lecture name: Saravanabalagi Ramachandran

## Online shopping App
- Student Name: Marcos Vinicius de Oliveira
- Student Number: 22931
- Semester: 2

## Requirements:
1 - Authentication:
- [x] Allow User to Signup
- [x] Log In using username and password
- [x] Store userID once logged in to keep the user logged in (even after restarting the app)

2 - Product Listing:
- [x] List Product Categories
- [x] On clicking a Category, list Products in that Category
- [x] On clicking a Product, show Product description, show buy button and controls to change quantity.

3 - Cart:
- [x] Show cart summary
- [x] Show total amount
- [x] Purchase button to place an order, show order notification

4 - Show order history:
- [ ] List users orders
- [ ] On clicking an Order, show Order details and Products ordered
- [x] On clicking a Product, take them to Product description page created for 3.3

5 - Show User details:
- [x] Use the stored userID to show user details
- [x] Show a random circular profile image from https://thispersondoesnotexist.com/
- [x] Show Logout button, on click take back to Signup / Log In page (Restart should not auto login after logout)

6 - UI/Implementations Requirements:
- [ ] RecyclerView used for all Lists: Categories, Products, Orders
- [ ] ViewPager2 with bottom TabLayout for: Shop, Orders, Profile icons
- [x] If logged in, attach authentication token to all requests until logout
- [x] Add a small "About this app" button in the profile page, that shows a page with your copyright details and credits.

## Screenshots:
[![1-Splashscreen.png](https://i.postimg.cc/NGX93fpG/1-Splashscreen.png)](https://postimg.cc/grY2RpC1)
[![2-Shop.png](https://i.postimg.cc/xCz8VtGp/2-Shop.png)](https://postimg.cc/FdhNjbB0)
[![3-Cart.png](https://i.postimg.cc/fbkkBwVN/3-Cart.png)](https://postimg.cc/yJ4VNChQ)
[![4-Delivery.png](https://i.postimg.cc/8C3cYqvr/4-Delivery.png)](https://postimg.cc/bGxqDV3z)
[![5-msg.png](https://i.postimg.cc/xTSJnD4v/5-msg.png)](https://postimg.cc/V5DLWT1v)
[![6-Login.png](https://i.postimg.cc/m2NcrXv0/6-Login.png)](https://postimg.cc/gnjcSDkD)
[![7-Forgot-Password.png](https://i.postimg.cc/kgTBQVXs/7-Forgot-Password.png)](https://postimg.cc/qNCJ0vK6)
[![8-Create-Account.png](https://i.postimg.cc/8zp7fdDs/8-Create-Account.png)](https://postimg.cc/ykG1wZDz)
[![9-user-profile.png](https://i.postimg.cc/MHxvq1p7/9-user-profile.png)](https://postimg.cc/LYCHVYf5)

## JSON API Links:
- Root URL: https://fakestoreapi.com
- User Signup: POST /users
- User Sign In: POST /auth/login
- Product Categories: GET /products/categories
- Products in a particular category: GET /products/categories/electronics
- Purchase a cart: POST /carts
- Retrieve order history for user id 2: GET /carts/user/2
- More details available on https://fakestoreapi.com/docs

## References:
- https://stackoverflow.com
- Firebase database: https://firebase.google.com/docs/auth/android/start
- Retrofit Documentation: https://square.github.io/retrofit
- MohammadReza Keikavousi: https://fakestoreapi.com
- Fakestore implementation: https://github.com/ashutosh1199
- Bottom Nav View: https://www.youtube.com/watch?v=Chso6xrJ6aU
- Communicating with fragments: https://developer.android.com/guide/fragments/communicate#kotlin
- Login page, splash page and others functions: https://www.youtube.com/watch?v=hoK9OOP1KZw&t=396s
- FCM Firebase Cloud Message: https://firebase.google.com/docs/cloud-messaging/android/client

## Copyright Disclaimer:
Please note that this app project is part of Dorset College's sophomore semester final project, however, it may contain some part of code that may be copyrighted, if so, please contact me so I can delete or give due to copyright. All the people were duly referenced in the "References" section above.

Please note that this project is non-profit or not intended to be monetized.

Regards,
Marcos Oliveira
