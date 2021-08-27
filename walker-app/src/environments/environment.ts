const urls: { [key: string]: any } = {
  auth: {
    prefix: 'auth',
    calls: {
      confirmUser: 'confirm/:token',
      loginUser: 'login',
      registerUser: 'register',
      registerAdmin: 'register/:token',
    },
  },
  user: {
    prefix: 'user',
    calls: {
      getUser: ':id',
      getAllUsers: 'all',
      getLoggedUserData: 'get_data',
      getUserData: 'get_data/:username',
      changeData: 'change_data',
      changePassword: 'change_password',
    },
  },
  owner: {
    prefix: 'owner',
    calls: {
      getDogs: 'dogs',
      getHistory: 'history',
      getSitters: 'sitters',
      getWalks: 'walks',
    },
  },
  sitter: {
    prefix: 'sitter',
    calls: {
      getDogs: 'dogs',
      getHistory: 'history',
      getWalks: 'walks',
    },
  },
  dog: {
    prefix: 'dog',
    calls: {
      createDog: 'create',
      getDog: ':dogId',
      deleteDog: 'delete_dog/:dogId',
    },
  },
  walk: {
    prefix: 'walk',
    calls: {
      getWalk: ':id',
      getAllWalks: 'all',
      createWalk: 'create',
      walkEnroll: 'enroll/:id',
      walkDisenroll: 'disenroll/:id',
      deleteWalk: ':id',
    },
  },
  review: {
    prefix: 'review',
    calls: {
      getDogReviews: 'dog/:dogId',
      getSitterReviews: 'sitter/:sitterId',
      addDogReview: 'dog/add',
      addSitterReview: 'sitter/add'
    }
  },
  image: {
    prefix: 'image',
    calls: {
      uploadDogImage: 'dog/upload/:dogId',
      getDogImage: 'dog/:dogId/:filename',
      uploadUserImage: 'user/upload/:userId',
      getUserImage: 'user/:userId/:filename',
    },
  },
};

export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080',
  urls,
};
