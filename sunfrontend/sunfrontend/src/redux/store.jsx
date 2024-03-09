import { configureStore } from '@reduxjs/toolkit'

import loinslice from './slices/login/index';

export const store = configureStore({
  reducer: {
    loginr: loinslice,
  },
})

// Infer the `RootState` and `AppDispatch` types from the store itself
// export type RootState;
// // // Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}

