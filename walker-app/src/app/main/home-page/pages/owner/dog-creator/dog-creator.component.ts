import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, Validator, Validators } from '@angular/forms';

@Component({
  selector: 'ktbz-dog-creator',
  templateUrl: './dog-creator.component.html',
  styleUrls: ['./dog-creator.component.scss']
})
export class DogCreatorComponent implements OnInit {

  constructor(private builder: FormBuilder,
    private ref: ChangeDetectorRef) { }

  dogForm = this.builder.group({
    name: ['', Validators.required],
    dogBreed: ['', Validators.required],
    dogType: ['', Validators.required],
    characteristic: ['', Validators.required],
    walkDuration: ['', Validators.required],
    walkIntensity: ['', Validators.required],
    walkDescription: ['', Validators.required]
  })

  get name() {
    return this.dogForm.get('name');
  }

  dogName: string = '';

  ngOnInit(): void {
    this.ref.detectChanges();
  }

}
