# Feature Issue Template Design

## Context

The repository does not currently contain a `.github` directory or GitHub issue templates. The project needs a feature-development issue template that applies the `feature` label by default and gives authors a clear place to describe the feature and its work items.

## Decision

Use GitHub Issue Forms YAML rather than a Markdown issue template. Issue Forms provide a structured GitHub UI, allow default labels, and keep the template easy to extend later without changing the authoring workflow.

## Scope

Create one issue form:

- Path: `.github/ISSUE_TEMPLATE/feature.yml`
- Purpose: create issues for feature development
- Default label: `feature`
- Main fields: feature description and task list

No application code, build configuration, or automated test behavior changes are required.

## Template Structure

The issue form will include the standard GitHub Issue Forms top-level fields:

- `name`: `Feature`
- `description`: identifies the template as a feature-development issue form
- `title`: a feature-oriented title prefix
- `labels`: `["feature"]`
- `body`: input definitions for the issue content

The form body will contain two required textarea fields:

- Feature description: asks the author to describe the feature and its purpose.
- Work items: asks the author to write a task list. The placeholder will show `- [ ]` checkbox examples so GitHub renders the result as a task list.

## Validation

Verification only needs to cover repository state and YAML correctness:

- Confirm `.github/ISSUE_TEMPLATE/feature.yml` exists.
- Confirm the form uses GitHub Issue Forms keys consistently.
- Confirm the default label is exactly `feature`.
- Confirm both required writing areas are present.

No Gradle test run is necessary because this change only affects GitHub repository metadata.
