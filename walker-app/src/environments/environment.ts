const urls: { [key: string]: any } = {
  admin: {
    prefix: 'admin',
    calls: {
      getActivity: 'activities',
      getDogs: 'dogs',
      getUsers: 'users',
      getWalks: 'walks',
      getReportsByStatus: 'report/:status',
      changeReportStatus: 'report/:reportId/:status',
      blockUser: 'block/:userId',
      unblockUser: 'unblock/:userId',
      banUser: 'ban/:userId',
      unbanUser: 'unban/:userId',
      removeUser: 'remove/:userId'
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
      getNotifications: 'notifications',
      getRole: 'role/:username',
      changeData: 'change_data',
      changePassword: 'change_password',
      changeDescription: 'change_description',
      changeAvatar: 'change_avatar',
      markNotificationAsRead: 'notifications/:notificationId/markAsRead',
      postReport: 'report'
    },
  },
  owner: {
    prefix: 'owner',
    calls: {
      getData: 'ownerData/:username',
      getDogs: 'dogs',
      getHistory: 'history',
      getWalks: 'walks',
      getOwnerImages: 'images'
    },
  },
  sitter: {
    prefix: 'sitter',
    calls: {
      getData: 'sitterData/:username',
      getHistory: 'history',
      getWalks: 'walks',
      getSitterImages: 'images',
      getSitterReviews: 'reviews'
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
