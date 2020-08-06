import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'wholesaler',
        loadChildren: () => import('./wholesaler/wholesaler.module').then(m => m.StoreWholesalerModule),
      },
      {
        path: 'distribution-store',
        loadChildren: () => import('./distribution-store/distribution-store.module').then(m => m.StoreDistributionStoreModule),
      },
      {
        path: 'user-location',
        loadChildren: () => import('./user-location/user-location.module').then(m => m.StoreUserLocationModule),
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.StoreProductModule),
      },
      {
        path: 'product-category',
        loadChildren: () => import('./product-category/product-category.module').then(m => m.StoreProductCategoryModule),
      },
      {
        path: 'product-sub-category',
        loadChildren: () => import('./product-sub-category/product-sub-category.module').then(m => m.StoreProductSubCategoryModule),
      },
      {
        path: 'add-on-product',
        loadChildren: () => import('./add-on-product/add-on-product.module').then(m => m.StoreAddOnProductModule),
      },
      {
        path: 'notification',
        loadChildren: () => import('./notification/notification.module').then(m => m.StoreNotificationModule),
      },
      {
        path: 'wallet',
        loadChildren: () => import('./wallet/wallet.module').then(m => m.StoreWalletModule),
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.StoreOrderModule),
      },
      {
        path: 'contact-us',
        loadChildren: () => import('./contact-us/contact-us.module').then(m => m.StoreContactUsModule),
      },
      {
        path: 'faq',
        loadChildren: () => import('./faq/faq.module').then(m => m.StoreFaqModule),
      },
      {
        path: 'terms-and-condition',
        loadChildren: () => import('./terms-and-condition/terms-and-condition.module').then(m => m.StoreTermsAndConditionModule),
      },
      {
        path: 'about-us',
        loadChildren: () => import('./about-us/about-us.module').then(m => m.StoreAboutUsModule),
      },
      {
        path: 'transactions',
        loadChildren: () => import('./transactions/transactions.module').then(m => m.StoreTransactionsModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class StoreEntityModule {}
