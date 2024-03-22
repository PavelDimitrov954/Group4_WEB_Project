# Movie Forum System

## Project Description
The Movie Forum System is an interactive platform for movie enthusiasts. It focuses on user engagement through discussions about various movies, where users can post, comment, and express their preferences through likes and dislikes.

## Functional Requirements

### Entities
- **Users:** Require first and last name, email, username, and password.
- **Admins:** Include first and last name, email, and optional phone number.
- **Posts:** Consist of a creator, title, content, comments, and likes count.

### Public Part
- Accessible without logging in.
- Homepage displays core features and usage statistics.
- Allows anonymous users to register and log in.
- View top 10 most commented and recently created posts.

### Private Part
- For authenticated users.
- Includes login/logout and profile updates (username can't be changed).
- Browse, sort, filter, and interact with posts.
- Post creation and personal post/comment management.

### Administrative Part
- User management (search, block/unblock).
- Post deletion and overall post management.
