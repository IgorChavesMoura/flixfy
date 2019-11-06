import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SingupComponent } from './components/singup/singup.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './services/AuthGuard';
import { CategoryListComponent } from './components/category/category-list/category-list.component';
import { CategoryDetailComponent } from './components/category/category-detail/category-detail.component';
import { FormatListComponent } from './components/format/format-list/format-list.component';
import { FormatDetailComponent } from './components/format/format-detail/format-detail.component';
import { VcDetailComponent } from './components/video-content/vc-detail/vc-detail.component';
import { VcEditComponent } from './components/video-content/vc-edit/vc-edit.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { UserContentComponent } from './components/user/user-content/user-content.component';
import { VcSearchComponent } from './components/video-content/vc-search/vc-search.component';
import { UserEditComponent } from './components/user/user-edit/user-edit.component';

const routes: Routes = [
  { path:'login', component:LoginComponent },
  { path:'signup', component:SingupComponent },
  { path:'home', component:HomeComponent, canActivate:[AuthGuard] },
  { path:'categories', component:CategoryListComponent, canActivate:[AuthGuard] },
  { path:'category/create', component:CategoryDetailComponent, canActivate:[AuthGuard] },
  { path:'category/:id', component:CategoryDetailComponent, canActivate:[AuthGuard] },
  { path:'formats', component:FormatListComponent, canActivate:[AuthGuard] },
  { path:'format/create', component:FormatDetailComponent, canActivate:[AuthGuard] },
  { path:'format/:id', component:FormatDetailComponent, canActivate:[AuthGuard] },
  { path:'vc/:id/detail', component:VcDetailComponent, canActivate:[AuthGuard] },
  { path:'vc/:id/edit', component:VcEditComponent, canActivate:[AuthGuard] },
  { path:'vc/create', component:VcEditComponent, canActivate:[AuthGuard] },
  { path:'vc/search', component:VcSearchComponent, canActivate:[AuthGuard] },
  { path:'users', component:UserListComponent, canActivate:[AuthGuard] },
  { path:'users/:id', component:UserContentComponent, canActivate:[AuthGuard] },
  { path:'user/update', component:UserEditComponent, canActivate:[AuthGuard]},
  { path:'', redirectTo:'/login', pathMatch:'full' },
  { path:'**', redirectTo:'/login', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
