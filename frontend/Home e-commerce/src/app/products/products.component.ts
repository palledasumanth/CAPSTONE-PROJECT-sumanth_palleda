import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  selectedFiles: FileList;
  currentFile: File;
  csvfiless:any;
  fg:FormGroup;
  csv:FormGroup;
  model:any={}
  cats:any[]
  list:any[]
  filePath="../assets/upload.png"
  image:string;
  submitted=false;
  upload=false;
  constructor(private api:ApiService,
    private fb:FormBuilder,
    private toast:ToastrService,
    private router:Router) { }

  ngOnInit(): void {
    this.createForm()
    this.loadData()
    this.createcsv()
  }

  loadData(){
    this.api.listcategories().subscribe({
      next:resp=>this.cats=resp
    })
    this.api.listproducts().subscribe({
      next:resp=>{
        this.list=resp;
      }
    })
  }

  createForm(){
     this.fg= this.fb.group({
       pname:['',Validators.required],
      category:['',Validators.required],
      descr:['',Validators.required],
      price:['',Validators.required],
      pic:[''],
      stocks:['',Validators.required],
      id:['0',Validators.required],
      photo:['']
    })
  }

  createcsv(){
    this.csv= this.fb.group({
      csvf:['',Validators.required]
   })
 }

  saveFile(e:any){
  
    this.selectedFiles = e.target.files;
    this.currentFile = this.selectedFiles.item(0);
    const fd = new FormData();
    fd.append('file', this.currentFile);  
       
           
    this.api.uploadfile(fd).subscribe(res => {
      console.log("Response ",res);
      this.image = res;
      console.log(this.image);
      
    }, err => {
      console.log("Error ",err);
      
    })
  
  }

  deleteProduct(id:number){
    this.api.deleteproduct(id).subscribe({
      next:resp=>{
        this.toast.success('Product deleted')
        this.loadData()
      },
      error:err=>this.toast.error('Product cannot delete')
    })
  }

  saveproduct(){  
    
    this.submitted=true;
    this.fg.get('id').setValue('0')
    if(this.fg.valid){
      
      this.fg.controls.photo.setValue(this.image);
      this.api.saveproduct(this.fg.value).subscribe({
        next:resp=>{              
        this.toast.success("Product saved successfully")      
        this.fg.reset()
        this.submitted=false;
        this.loadData()
        },
        error:err=>console.log(err.error)
      });
    
    }
    
  }

  savecsv(e){  
    
    // this.submitted=true;
    // this.fg.get('id').setValue('0')
    // if(this.fg.valid){
      
    //   this.fg.controls.photo.setValue(this.image);
    //   this.api.saveproduct(this.fg.value).subscribe({
    //     next:resp=>{              
    //     this.toast.success("Product saved successfully")      
    //     this.fg.reset()
    //     this.submitted=false;
    //     this.loadData()
    //     },
    //     error:err=>console.log(err.error)
    //   });
    
    //}
    this.selectedFiles = e.target.files;
    this.currentFile = this.selectedFiles.item(0);
    const fd = new FormData();
    fd.append('file', this.currentFile);  
       console.log(fd);
       this.csvfiless=fd;
    
    
  }

  onSubmit(){
console.log(this.csvfiless);
this.api.uploadcsv(this.csvfiless).subscribe(res=>{
  console.log(res);
  this.toast.success("Product saved successfully by csv files")      
  this.csv.reset()
  this.submitted=false;
  this.loadData()
})
  }


  EmailProduct(){

    this.router.navigateByUrl("emailus");
  }

}

