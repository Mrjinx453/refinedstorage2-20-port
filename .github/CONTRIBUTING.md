# Contributing to Refined Storage

## Versioning

This project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Pull requests

- Keep your PR as small as possible, this makes reviewing easier.
- Commits serve a clear purpose and have a fitting commit message.
- Branches are kept up to date by rebasing, preferably.
- Changes are added in `CHANGELOG.md`. Please refrain from using technical terminology, keep it user-friendly. The
  format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## Gitflow

This project uses [Gitflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).

## Documentation

If you are adding functionality or are changing behavior, please update the Patchouli documentation.

## Code style

Please use the `.editorconfig` file as provided.

## Testing

Changes to the `core` module need unit tests.

Changes to platform modules like `fabric` don't necessarily need these, but they are welcome.