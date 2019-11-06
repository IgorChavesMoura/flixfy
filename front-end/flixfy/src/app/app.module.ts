import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { FormsModule } from '@angular/forms'
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SingupComponent } from './components/singup/singup.component';
import { HomeComponent } from './components/home/home.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { CategoryListComponent } from './components/category/category-list/category-list.component';
import { CategoryDetailComponent } from './components/category/category-detail/category-detail.component';
import { FormatListComponent } from './components/format/format-list/format-list.component';
import { FormatDetailComponent } from './components/format/format-detail/format-detail.component';
import { VcListComponent } from './components/video-content/vc-list/vc-list.component';
import { VcDetailComponent } from './components/video-content/vc-detail/vc-detail.component';
import { VcEditComponent } from './components/video-content/vc-edit/vc-edit.component';
import { VcListItemComponent } from './components/video-content/vc-list-item/vc-list-item.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { UserContentComponent } from './components/user/user-content/user-content.component';
import { VcSearchComponent } from './components/video-content/vc-search/vc-search.component';
import { UserEditComponent } from './components/user/user-edit/user-edit.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SingupComponent,
    HomeComponent,
    SidebarComponent,
    CategoryListComponent,
    CategoryDetailComponent,
    FormatListComponent,
    FormatDetailComponent,
    VcListComponent,
    VcDetailComponent,
    VcEditComponent,
    VcListItemComponent,
    UserListComponent,
    UserContentComponent,
    VcSearchComponent,
    UserEditComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
