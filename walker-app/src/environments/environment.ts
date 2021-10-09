const urls: { [key: string]: any } = {
  admin: {
    prefix: 'admin',
    calls: {
      getUsers: 'users',
      getDogs: 'dogs',
      getWalks: 'walks'
    }
  },
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
      getUserData: 'get_data/:username',
      changeData: 'change_data',
      changePassword: 'change_password',
      changeDescription: 'change_description',
      changeAvatar: 'change_avatar',
      getRole: 'role/:username'
    },
  },
  owner: {
    prefix: 'owner',
    calls: {
      getData: 'ownerData/:username',
      getDogs: 'dogs',
      getHistory: 'history',
      getSitters: 'sitters',
      getWalks: 'walks',
    },
  },
  sitter: {
    prefix: 'sitter',
    calls: {
      getData: 'sitterData/:username',
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
      getWalkCard: ':id',
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
      uploadDogReviewImage: 'review/upload/:reviewId',
    },
  },
};

export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080',
  urls,
};
