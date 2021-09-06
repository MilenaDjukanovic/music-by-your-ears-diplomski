export interface IPageable {
  page?: number;
  size?: number;
  sort?: string[];
}

export class Pageable implements IPageable {
  constructor(public page?: number,
              public size?: number,
              public sort?: string[]) {
  }
}

export interface IPage {
  content?: any[];
  totalElements?: number;
}

export class Page implements IPage {
  constructor(public content?: any[],
              public totalElements?: number) {
  }
}

